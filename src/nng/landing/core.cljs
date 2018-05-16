(ns nng.landing.core
  (:import )
  (:require [goog.events :as evt]
            [goog.dom :as dom]))

(enable-console-print!)

(println "This text is printed from src/nng.landing/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defn q [d] (js/document.querySelectorAll d))
(defn logAttr [d] (println (clj->js [d])))


#_(defonce vue (new js/Vue (js-obj "el" "#app"
                                 "data" (js-obj "message" "안녕하세요"
                                                "showMenu" false
                                                "shrink" "")
                                 "methods" (js-obj "scrollSmooth"
                                                   (fn [e]
                                                     (js/window.scroll
                                                      (js-obj
                                                       "top" (-> (q e.target.hash) (aget 0) .-offsetTop)
                                                       "left" 0
                                                       "behavior" "smooth"))
                                                     (set! vue.showMenu false)
                                                     (.preventDefault e)
                                                     false)))))

#_(defonce vue (new js/Vue (js-obj "el" "#app")))
(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
  ;; $('.js-scroll-trigger').click(function() {
  ;;   $('.navbar-collapse').collapse('hide');
  ;; });

  (let [data01 (js-obj "message" "안녕하세요"
                        "showMenu" false
                        "shrink" "")
         method01 (js-obj "scrollSmooth"
                          (fn [e]
                            (js/window.scroll
                             (js-obj
                              "top" (- (-> (q e.target.hash) (aget 0) .-offsetTop) 48)
                              "left" 0
                              "behavior" "smooth"))
                            (set! vue.showMenu false)
                            (.preventDefault e)
                            false))
         vue (new js/Vue (js-obj "el" "#app" "data" data01 "methods" method01))]

    (evt/removeAll js/window)

    (evt/listen js/window "scroll"
                #(if (> js/document.scrollingElement.scrollTop 100)
                   (set! vue.shrink "navbar-shrink")
                   (set! vue.shrink "")))

    (evt/removeAll js/window)

    (evt/listen js/window "scroll"
                #(if (> js/document.scrollingElement.scrollTop 100)
                   (set! vue.shrink "navbar-shrink")
                   (set! vue.shrink "")))
    )
)

(on-js-reload)

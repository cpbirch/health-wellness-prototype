(ns hw-proto.core
    (:require [reagent.core :as reagent :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [hw-proto.bmi :as bmi]
              [hw-proto.goals :as goals]))


;; -------------------------
;; Components




;; -------------------------
;; Views

(defn home-page []
  [:div {:class "container"}
   [:div {:class "row"} [:a {:href "/" :title "go to the home page"} [:img {:src "/images/header.png" :class "mast-head "}]] ]
   ;[:h2 "Welcome to hw-proto"]
   [:div {:class "row"} [:a {:href "/about"} "go to about page"]]
   [bmi/bmi-component]
   [:div {:class "row"}
    [:div {:class "col"} [goals/todo-app]]
    [:div {:class "col"}]]
   ])

(defn about-page []
  [:div {:class "container"}
   [:div [:a {:href "/" :title "go to the home page"} [:img {:src "/images/header.png" :class "mast-head "}]] ]
   ;[:h2 "About hw-proto"]
   [:div [:a {:href "/"} "go to the home page"]]])

;; -------------------------
;; Routes

(def page (atom #'home-page))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))

(secretary/defroute "/about" []
  (reset! page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))

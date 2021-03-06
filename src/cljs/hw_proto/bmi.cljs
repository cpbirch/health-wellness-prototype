(ns hw-proto.bmi
  (:require [reagent.core :as reagent :refer [atom]]))

(def bmi-data (reagent/atom {:height 180 :weight 80}))

(defn calc-bmi []
  (let [{:keys [height weight bmi] :as data} @bmi-data
        h (/ height 100)]
    (if (nil? bmi)
      (assoc data :bmi (/ weight (* h h)))
      (assoc data :weight (* bmi h h)))))

(defn slider [param value min max]
  [:input {:type "range" :value value :min min :max max
           :style {:width "100%"}
           :on-change (fn [e]
                        (swap! bmi-data assoc param (.-target.value e))
                        (when (not= param :bmi)
                          (swap! bmi-data assoc :bmi nil)))}])

(defn diet [diagnosis]
    [:img {:src (str "/images/" diagnosis "-diet.png") :class "diet"}])

(defn bmi-component []
  (let [{:keys [weight height bmi]} (calc-bmi)
        [color diagnose] (cond
                           (< bmi 18.5) ["orange" "underweight"]
                           (< bmi 25) ["inherit" "normal"]
                           (< bmi 30) ["orange" "overweight"]
                           :else ["red" "obese"])]
    [:div {:class "row"}
     [:div {:class "col"}
      [:section#bmi
       [:header#header
        [:h1 "BMI calculator"]]
       [:div
        "Height: " (int height) "cm"
        [slider :height height 100 220]]
       [:div
        "Weight: " (int weight) "kg"
        [slider :weight weight 30 150]]
       [:div
        "BMI: " (int bmi) " "
        [:span {:style {:color color}} diagnose]
        [slider :bmi bmi 10 50]]]]
     [:div {:class "col"} [diet diagnose]]]))
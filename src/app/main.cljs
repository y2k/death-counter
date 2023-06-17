(ns app.main
  (:require [app.diff.diff-js :as diffjs]
            [app.diff.diff-core :as diff]))

(defonce current-vdom (atom nil))
(add-watch current-vdom nil (fn [_ _ o n] (if (not= o n) (diff/diff (diffjs/JsRenderer.) o n))))

(defonce app-state (atom {:count 1}))

(defn view [state]
  [:div {}
   [:style {:innerText "
    .root { position: fixed; width: 100%; height: 100%; display: flex; flex-direction: column; justify-content: center; padding: 16px; }
    .root > *:not(:last-child) { margin-bottom: 32px; }
    .counter { font-size: 10rem; text-align: center; }
    .buttons { display: flex; }
    .buttons > *:not(:last-child) { margin-right: 16px; }
    .buttons__button { flex: 1 }"}]
   [:div {:className "root"}
    [:span {:className "counter" :innerText (str (:count state))}]
    [:div {:className "buttons"}
     [:button {:className "buttons__button" :innerText "-"
               :onclick (diffjs/with-tag (fn [] (swap! app-state update-in [:count] dec)) ::minus)}]
     [:button {:className "buttons__button" :innerText "+"
               :onclick (diffjs/with-tag (fn [] (swap! app-state update-in [:count] inc)) ::plus)}]]]])

(add-watch app-state nil (fn [_ _ _ n] (reset! current-vdom (view n))))
(reset! current-vdom (view @app-state))

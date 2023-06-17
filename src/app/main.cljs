(ns app.main)

(defn increase []
  (let [node (.querySelector js/document ".count_button")
        count (js/parseInt (. node -innerText))]
    (set! (. node -innerText) (+ 1 count))))

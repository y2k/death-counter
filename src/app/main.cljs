(ns app.main)

(let [node (.querySelector js/document ".count_button")
      count (js/parseInt (. node -innerText))]
  (set! (. node -innerText) 0)
  (set!
   (.-onclick node)
   (fn []
     (let [count (js/parseInt (. node -innerText))]
       (set! (. node -innerText) (+ 1 count))))))

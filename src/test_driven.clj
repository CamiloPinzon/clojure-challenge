(ns test_driven
  (:require [clojure.test :refer :all]
            [invoice-item]
            )
  )

(defn simulate-subtotal
  [data]
  (let [{:keys [precise-quantity precise-price discount-rate]
         :or {precise-quantity 0
              precise-price 0
              discount-rate 0}} data]
    (* precise-price precise-quantity (- 1 (/ discount-rate 100.0)))
  )
)

(deftest test-no-discount
  (let [data {:precise-quantity 5
              :precise-price 10
              :discount-rate 0}
        result (simulate-subtotal data)]
    (is (<= (Math/abs (- 50.0 result)) 0.0001))
  )
)

(deftest test-with-discount
  (let [data {:precise-quantity 3
              :precise-price 20
              :discount-rate 15}
        result (simulate-subtotal data)]
    (is (<= (Math/abs (- 51.0 result)) 0.0001))
  )
)

(deftest test-no-quantity
  (let [data {:precise-price 10
              :discount-rate 5}
        result (simulate-subtotal data)]
    (is (<= (Math/abs (- 0.0 result)) 0.0001))
  )
)

(deftest test-no-price
  (let [data {:precise-quantity 3
              :discount-rate 15}
        result (simulate-subtotal data)]
    (is (<= (Math/abs (- 0 result)) 0.0001))
  )
)

(deftest test-no-precise-quantity
  (let [data {:precise-price 10
              :discount-rate 5}
        result (simulate-subtotal data)]
    (is (<= (Math/abs (- 0 result)) 0.0001))
  )
)

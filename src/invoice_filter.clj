(ns invoice_filter)

(ns invoice_filter)

(defn filter-items [invoice]
  (->> invoice
       :invoice/items
       (filter (fn [item]
                 (let [iva-condition (some (fn [tax]
                                             (and (= (:tax/category tax) :iva)
                                                  (= (:tax/rate tax) 19)))
                                           (:taxable/taxes item))
                       ret-condition (some (fn [retention]
                                             (and (= (:retention/category retention) :ret_fuente)
                                                  (= (:retention/rate retention) 1)))
                                           (:retentionable/retentions item))]
                   (and (not= iva-condition ret-condition)
                        (or iva-condition ret-condition)))))))

(def invoice (clojure.edn/read-string (slurp "../invoice.edn")))
(println "Filtered Items:" (filter-items invoice))
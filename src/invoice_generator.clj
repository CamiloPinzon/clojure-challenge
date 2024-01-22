(ns invoice_generator
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]
            [clojure.spec.alpha :as alpha]
            [invoice-spec :as invoice-spec]))

(defn read-json-from-file
  [file-name]
  (with-open
    [reader (io/reader file-name)]
    (json/read reader)
  )
)

(defn json-to-map
  [json-data]
  (json/read-str (str json-data) :key-fn keyword)
)

(defn validate-invoice
  [invoice]
  (alpha/valid? ::invoice-spec/invoice invoice )
)

(def json-data (read-json-from-file "../invoice.json"))
(def invoice-map (json-to-map json-data))
(def invoice-map (validate-invoice invoice-map))
(println "JSON Data:" invoice-map)

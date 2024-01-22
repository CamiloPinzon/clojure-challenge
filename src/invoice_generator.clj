(ns invoice_generator
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

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

(def json-data (read-json-from-file "../invoice.json"))
(def invoice-map (json-to-map json-data))
(println "JSON Data:" invoice-map)

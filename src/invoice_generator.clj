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

(def json-data (read-json-from-file "../invoice.json"))
(println "JSON Data:" json-data)

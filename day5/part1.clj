(ns day5.part1
  (:require
   [clojure.java.io :as io]))

(defn solve [input])

(with-open [rdr (io/reader "day4/sample1.txt")]
  (solve (line-seq rdr)))

(with-open [rdr (io/reader "day4/input.txt")]
  (solve (line-seq rdr)))


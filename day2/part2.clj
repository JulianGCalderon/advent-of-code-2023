(ns day2.part2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [day2.part1 :refer [parse-game]]))

(def colors ["red" "green" "blue"])

(defn minimum-set [game]
  (reduce
   (partial merge-with max)
   (zipmap colors (repeat 0))
   (:rounds game)))

(defn power [set]
  (reduce * (vals set)))

(defn solve [rdr]
  (->> (line-seq rdr)
       (map parse-game)
       (map minimum-set)
       (map power)
       (reduce +)))

(with-open [rdr (io/reader "day2/sample2.txt")]
  (solve rdr))

(with-open [rdr (io/reader "day2/input.txt")]
  (solve rdr))


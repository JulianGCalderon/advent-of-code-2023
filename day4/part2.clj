(ns day4.part2
  (:require
   [clojure.java.io :as io]
   [day4.part1 :refer [winners]]))

(defn push-stash [winners [current & remaining]]
  (map (partial +)
       remaining
       (concat (take winners (repeat (inc current)))
               (repeat 0))))

(defn solve [input]
  (loop [[cur-winners & rem-winners] (map winners input)
         [cur-stash & _ :as stash] (repeat 0)
         total 0]
    (let [new-total (+ total 1 cur-stash)]
      (if (empty? rem-winners)
        new-total
        (recur rem-winners (push-stash cur-winners stash) new-total)))))

(with-open [rdr (io/reader "day4/sample2.txt")]
  (solve (line-seq rdr)))

(with-open [rdr (io/reader "day4/input.txt")]
  (solve (line-seq rdr)))


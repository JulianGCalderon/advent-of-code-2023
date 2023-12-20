(ns day3.part2
  (:require
   [clojure.java.io :as io]
   [clojure.set :refer [difference]]
   [day3.part1 :refer [input-find-all neighbours symbol-coord]]))

(defn in-range? [gear {:keys [start end row _]}]
  (let [indices (for [x (range start end)] [row x])
        all-coords  (set (mapcat neighbours indices))
        outer-coords (difference all-coords (set indices))]
    (contains? outer-coords (symbol-coord gear))))

(defn gear-score [numbers gear]
  (let [in-range (filter (partial in-range? gear) numbers)]
    (if (= 2 (count in-range))
      (->> (map :group in-range)
           (map #(Integer. %))
           (reduce *))
      0)))

(defn solve [input]
  (let [numbers (input-find-all #"\d+" input)]
    (->> (input-find-all #"\*" input)
         (map (partial gear-score numbers))
         (reduce +))))

(with-open [rdr (io/reader "day3/input.txt")]
  (solve (line-seq rdr)))

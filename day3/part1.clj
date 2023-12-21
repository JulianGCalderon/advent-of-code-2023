(ns day3.part1
  (:require
   [clojure.java.io :as io]
   [clojure.set :refer [difference]]))

(defn neighbours [position]
  (for [i (range -1 2)
        j (range -1 2)]
    (map + [i j] position)))

(defn string-find-all [pattern string]
  (let [matcher (re-matcher pattern string)]
    (for [_ (range)
          :while (.find matcher)]
      {:start (.start matcher)
       :end (.end matcher)
       :group (.group matcher)})))

(defn input-find-all [pattern input]
  (->> input
       (map-indexed (fn [row line]
                      (mapv #(assoc %1 :row row)
                            (string-find-all pattern line))))
       (flatten)))

(defn symbol-coord [symbol]
  [(:row symbol) (:start symbol)])

(defn engine-part? [{:keys [start end row _]} symbols]
  (let [indices (for [x (range start end)] [row x])
        all-coords  (set (mapcat neighbours indices))
        outer-coords (difference all-coords (set indices))
        symbol-coords (set (map symbol-coord symbols))]
    (some
     (partial contains? symbol-coords)
     outer-coords)))

(defn solve [input]
  (let [symbols (input-find-all #"[^\d\.]" input)]
    (->> input
         (input-find-all #"\d+")
         (filter #(engine-part? % symbols))
         (map :group)
         (map #(Integer/parseInt %))
         (reduce +))))

(with-open [rdr (io/reader "day3/sample1.txt")]
  (solve (line-seq rdr)))

(with-open [rdr (io/reader "day3/input.txt")]
  (solve (line-seq rdr)))

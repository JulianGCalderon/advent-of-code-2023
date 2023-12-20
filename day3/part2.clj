(ns day3.part2
  (:require
   [clojure.java.io :as io]
   [clojure.string :as string]
   [clojure.set :refer [union]]))

(def sample "467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...$.*....
.664.598..")

(defn symbols [input]
  (for [[i line] (map-indexed vector (string/split-lines input))
        [j ch] (map-indexed vector (string/split line #""))
        :when (re-matches #"\*" ch)]
    [i j]))

(defn expand-symbol [position]
  (set (for [i (range -1 2)
             j (range -1 2)]
         (map + [i j] position))))

(defn re-seq-pos [pattern string]
  (let [matcher (re-matcher pattern string)]
    (loop [matches []]
      (if (.find matcher)
        (recur (conj matches
                     {:start (.start matcher)
                      :end (.end matcher)
                      :group (Integer. (.group matcher))}))
        matches))))

(defn solve [input]
  (let [gears (symbols input)]
    (reduce +
            (mapv #(reduce * (get %1 1))
                  (filter
                   #(= (count (get %1 1)) 2)
                   (reduce
                    (fn [m number]
                      (reduce
                       #(update %1 (get %2 1) (fn [old] (conj old (get-in %2 [0 :group]))))
                       m
                       (for [gear gears
                             :when (some #(contains? (expand-symbol gear) [(:line number) %1]) (range (:start number) (:end number)))]
                         [number gear])))

                    {} (for [[i line] (map-indexed vector (string/split-lines input))
                             number (re-seq-pos #"\d+" line)]
                         (assoc number :line i))))))))

(solve (slurp "day3/input.txt"))

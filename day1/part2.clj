(ns day1.part2
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def translation {"one" "1"
                  "two" "2"
                  "three" "3"
                  "four" "4"
                  "five" "5"
                  "six" "6"
                  "seven" "7"
                  "eight" "8"
                  "nine" "9"})

(defn translate [digit] (or (translation digit) digit))

(def text-digits-pattern
  (->> (keys translation)
       (string/join "|")
       (re-pattern)))

(def digits-pattern
  (->> text-digits-pattern
       (str #"\d|")
       (re-pattern)))

(def reverse-digits-pattern
  (->> text-digits-pattern
       (str)
       (string/reverse)
       (str #"\d|")
       (re-pattern)))

(defn find-first [line]
  (translate (re-find digits-pattern line)))

(defn find-last [line]
  (->> (string/reverse line)
       (re-find reverse-digits-pattern)
       (string/reverse)
       (translate)))

(defn calibration-values [line]
  (let [n1 (find-first line)
        n2 (find-last line)]

    (Integer. (str n1 n2))))

(defn solve [rdr]
  (->> (line-seq rdr)
       (map calibration-values)
       (reduce +)))

(with-open [rdr (io/reader "day1/input.txt")]
  (solve rdr))



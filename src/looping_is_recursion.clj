(ns looping-is-recursion)

(defn power [base exp]
  (if (= base 0) 0
      (let [pow-helper (fn [acc n]
                         (if (= n 0) acc
                             (recur (* acc base) (- n 1))))]
        (pow-helper 1 exp))))

(power 2 2)  ;=> 4
(power 5 3)  ;=> 125
(power 7 0)  ;=> 1
(power 0 10) ;=> 0


(defn last-element [a-seq]
  (cond
    (empty? a-seq) nil
    (empty? (rest a-seq)) (first a-seq)
    :else (recur (rest a-seq))))


(last-element [])      ;=> nil
(last-element [1 2 3]) ;=> 3
(last-element [2 5])   ;=> 5

(defn seq= [seq1 seq2]
  (cond
    (and (empty? seq1) (empty? seq2)) true
    (or (empty? seq1) (empty? seq2)) false
    :else (recur (rest seq1) (rest seq2))))

(seq= [1 2 4] '(1 2 4))  ;=> true
(seq= [1 2 3] [1 2 3 4]) ;=> false
(seq= [1 3 5] [])        ;=> false

(defn find-first-index [pred a-seq]
  ":(")

(defn avg [a-seq]
  -1)

(defn parity [a-seq]
  ":(")

(defn fast-fibo [n]
  ":(")

(defn cut-at-repetition [a-seq]
  [":("])


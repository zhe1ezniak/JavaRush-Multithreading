package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public int hashCode() {
        if (first == null || last == null) return 31;
        return 31 * first.hashCode() + last.hashCode();
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null && o == null) return true;
        if (this!= null && o == null) return false;
        if (!(o instanceof Solution)) return false;
        Solution solution = (Solution) o;
        return ((solution.first == null && this.first == null) || solution.first.equals(this.first)) &&
                ((solution.last == null && this.last == null) || solution.last.equals(this.last));
    }
}

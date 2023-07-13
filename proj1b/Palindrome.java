public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    // need to use the structure that we created before.
    public boolean isPalindrome(String word) {
//        int length = word.length();
//        for (int i = 0; i < length / 2; i++) {
//            if (word.charAt(i) != word.charAt(length-i-1)) {
//                return false;
//            }
//        }
//        return true;
        Deque<Character> deque = wordToDeque(word);
        while (deque.size() != 1 && deque.size() != 0) {
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> deque = wordToDeque(word);
        while (deque.size() != 1 && deque.size() != 0) {
            Character first = deque.removeFirst();
            Character last = deque.removeLast();
            if (cc.equalChars(first, last)) {
                return false;
            }
        }
        return true;
    }

}

# Using PMD

Pick a Java project from Github (see the [instructions](../sujet.md) for suggestions). Run PMD on its source code using any ruleset. Describe below an issue found by PMD that you think should be solved (true positive) and include below the changes you would add to the source code. Describe below an issue found by PMD that is not worth solving (false negative). Explain why you would not solve this issue.

## Answer
Apache Commons Collections Project  
Erreurs detectées  
      `/home/elodie/Documents/VV/TP/TP2/commons-collections-master/src/test/java/org/apache/commons/collections4/set/AbstractSortedSetTest.java:283: ReturnEmptyCollectionRatherThanNull:    Return an empty collection rather than null.`
- True positive
```java

private SortedSet<E> getSubSet(final SortedSet<E> set) {​​​​​​

            final E[] elements = AbstractSortedSetTest.this.getFullElements();

            switch (m_Type) {​​​​​​

            case TYPE_SUBSET :

                return set.subSet(elements[m_LowBound], elements[m_HighBound]);

            case TYPE_HEADSET :

                return set.headSet(elements[m_HighBound]);

            case TYPE_TAILSET :

                return set.tailSet(elements[m_LowBound]);

            default :

                return null;

            }​​​​​​

        }​​​​​​


```
Dans cette fonction elle retourne un `null` à un moment donné pratique deconseillée et pour resoudre on peut retourner un ensemble vide ` return new SortedSet<E>();  `.

- false negative  

`/home/elodie/Documents/VV/TP/TP2/commons-collections-master/src/test/java/org/apache/commons/collections4/trie/PatriciaTrieTest.java:353:    LocalVariableNamingConventions:    The final local variable name 'char_b' doesn't match '[a-z][a-zA-Z0-9]*' `  

code correspondant  
 ` final char char_b = 'b';`   
l'erreur n'est pas pertinente car selon les standards, le nom d'une variable peut commencer ou contenir le caractere '_' . 


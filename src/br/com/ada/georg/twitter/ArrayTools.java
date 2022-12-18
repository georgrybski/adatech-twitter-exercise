package br.com.ada.georg.twitter;

public class ArrayTools {

    private ArrayTools() {
    }

    public static Object[] returnExpandedArray(Object[] oldArray) {
        var newArray = new Object[oldArray.length+50];
        transferFromTo(oldArray,newArray);
        return newArray;
    }

    public static void transferFromTo(Object[] fromArray, Object[] toArray) {
        if (fromArray.length < toArray.length){
            for (int i = 0; i < fromArray.length ; i++) {
                if(fromArray[i] != null){
                    toArray[i] = fromArray[i];
                }
            }
        }
    }

    public static boolean isArrayFull(Object[] array, int arrayCounter) {
         return arrayCounter == array.length-1;
    }

    public static Object[] expandArrayIfNecessary(Object[] array, int arrayCounter) {
        if (isArrayFull(array, arrayCounter)) {
            return returnExpandedArray(array);
        }
        else {
            return array;
        }
    }


}

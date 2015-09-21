package ua.com.master.help;



import ua.com.master.model.PersonStatus;

import java.util.HashSet;
import java.util.Set;


/**
 * @author ""
 * @version 1.0
 */
public class SetHelper
{
    public static boolean isSetContainsObj(Set<PersonStatus> set, PersonStatus obj, Class cl)
    {
      if(set==null || set.isEmpty() || obj==null
              || !obj.getClass().equals(cl)) return false;
      if(set.contains(obj))return true;
      return false;
    }
    public static void addToSetObject(Set<PersonStatus> sourceSet, PersonStatus obj, Class cl)
    {
     if(obj==null || !obj.getClass().equals(cl)) return ;
     if(sourceSet==null)sourceSet=new HashSet();
     if(!isSetContainsObj(sourceSet, obj, cl))
     {
       sourceSet.add(obj);
     }
    }
    public static void removeFromSetObject(Set<PersonStatus> sourceSet, PersonStatus obj,  Class cl)
    {
     if(sourceSet==null || sourceSet.isEmpty() || obj==null || !obj.getClass().equals(cl)) return ;
     if(isSetContainsObj(sourceSet, obj, cl))
     {
       sourceSet.remove(obj);
     }
    }

    public static class CompareObject
    {
      static int equals(Object obj1, Object obj2){
          return ((Comparable)obj1).compareTo(obj2);
      }
      static boolean Equals(Object obj1, Object obj2){

           int res=((Comparable)obj1).compareTo(obj2);
           return res==0?true:false;
      }
    }

}

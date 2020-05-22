
/**
 * @author shuyang
 * @Description:
 * @create 2020-05-22 16:34
 */


public class Test {

    @org.junit.Test
    public void tets(){
        String systemType = System.getProperties().getProperty("os.name");
        if ("Windo".equals(systemType.substring(0,5))){
            System.out.println("windowssssssss");
        }else if ("Linux".equals(systemType)){
            System.out.println("no");
        }
    }
}

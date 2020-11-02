package commonUtils;

import model.Result;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author masgak
 */
public class ApiUtils {
    private static String api = "https://api.wangpinpin.com/";
    /**
     * 舔狗日记
     */
    private static String dogLick = "0c97d296-e5b1-11ea-9d4b-00163e1e93a5";
    /**
     * 打工人语录
     */
    private static String workQuotation = "cbb26a26-14df-11eb-9d4b-00163e1e93a5";
    /**
     * 网抑云
     */
    private static String netCloud = "f67a73f4-e5b7-11ea-9d4b-00163e1e93a5";
    /**
     * 诗句
     */
    private static String verse = "c4c2abc1-e5b7-11ea-9d4b-00163e1e93a5";
    /**
     * 动漫
     */
    private static String comic = "0760fbcd-e5b8-11ea-9d4b-00163e1e93a5";

    private String getType(){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> map = new HashMap<>();
        map.put("t","DOG");
        Result result = restTemplate.getForObject(api+"unAuth/findTypeList"+"?=t={t}",Result.class,map);
        return null;
    }

    public String getQuotation(String type){
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> map = new HashMap<>();
        map.put("typeId",type);
        Result result = restTemplate.getForObject(api+"unAuth/getDoglickingDiary"+"?=typeId={typeId}",Result.class,map);
        if (result != null){
            return result.getData().toString();
        }else {
            return  "服务器爆炸无信息";
        }
    }

    public String getEveryDayText(){
        RestTemplate restTemplate = new RestTemplate();
        Result result = restTemplate.getForObject(api+"unAuth/getEveryDayText",Result.class);
        System.out.println(result.toString());
        return null;
    }
}

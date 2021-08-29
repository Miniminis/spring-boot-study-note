package com.example.objectmapper;

import com.example.objectmapper.domains.Car;
import com.example.objectmapper.domains.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ObjectmapperApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * springBoot 에서 사용하고 있는 기본 object mapper
     * // https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-mapper-asl
     * implementation group: 'org.codehaus.jackson', name: 'jackson-mapper-asl', version: '1.9.13'
     * */

    @Test
    void name() {
        System.out.println("tester main");
    }

    @Test
    void objectMapperTester() throws JsonProcessingException {

        User user = new User();
        user.setName("Hong Gil Dong");
        user.setAge(10);

        Car car1 = new Car();
        car1.setName("K5");
        car1.setCarNumber("12가 1234");
        car1.setType("sedan");

        Car car2 = new Car();
        car2.setName("K7");
        car2.setCarNumber("11가 6357");
        car2.setType("SUV");

        List<Car> cars = Arrays.asList(car1, car2);
        user.setCars(cars);

        System.out.println(user);
        //User{name='Hong Gil Dong', age=10, cars=[Car{name='K5', carNumber='12가 1234', type='sedan'}, Car{name='K7', carNumber='11가 6357', type='SUV'}]}

        /** json object 가져오기 */
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);
        //{"name":"Hong Gil Dong","age":10,"cars":[{"name":"K5","carNumber":"12가 1234","type":"sedan"},{"name":"K7","carNumber":"11가 6357","type":"SUV"}]}

        /** json value 가져오기 */
        JsonNode jsonNode = objectMapper.readTree(json);
        String _name = jsonNode.get("name").asText();
        Integer _age = jsonNode.get("age").asInt();

        System.out.println(_name);
        System.out.println(_age);
        //Hong Gil Dong
        //10

        /** json array 가져오기 */
        JsonNode carArr = jsonNode.get("cars");
        ArrayNode arrayNode = (ArrayNode) carArr;

        List<Car> _cars = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() { });
        System.out.println(_cars);
        //[Car{name='K5', carNumber='12가 1234', type='sedan'}, Car{name='K7', carNumber='11가 6357', type='SUV'}]


        /** json object 요소 변경하기, 예쁘게 출력하기 */
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.put("name", "tom");
        objectNode.put("age", 120);

        System.out.println(objectNode.toPrettyString());

//        {
//            "name" : "tom",
//            "age" : 120,
//            "cars" : [
//            {
//              "name" : "K5",
//                "carNumber" : "12가 1234",
//                "type" : "sedan"
//            },
//            {
//                "name" : "K7",
//                "carNumber" : "11가 6357",
//                "type" : "SUV"
//             }
//             ]
//        }


    }
}

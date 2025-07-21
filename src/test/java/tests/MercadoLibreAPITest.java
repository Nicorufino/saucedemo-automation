package tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpUtil;
import java.io.IOException;

public class MercadoLibreAPITest {

    @Test
    public void verificarDepartamentos() throws IOException, InterruptedException {
        String url = "https://www.mercadolibre.com.ar/menu/departments";
        String jsonResponse = HttpUtil.get(url);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);
        Assert.assertTrue(rootNode.isObject(), "La respuesta debe ser un objeto JSON");

        JsonNode departmentsNode = rootNode.get("departments");
        Assert.assertNotNull(departmentsNode, "El JSON debe contener el campo 'departments'");
        Assert.assertTrue(departmentsNode.isArray(), "El campo 'departments' debe ser un arreglo JSON");
        Assert.assertTrue(departmentsNode.size() > 0, "Debe haber al menos un departamento");

        JsonNode primerDepto = departmentsNode.get(0);
        Assert.assertTrue(primerDepto.has("name"), "El departamento debe tener un campo 'name'");
    }
}

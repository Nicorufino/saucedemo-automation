Este es un framework de automatización en Java utilizando Selenium WebDriver y TestNG para probar funcionalidades del sitio https://www.saucedemo.com.

Requisitos

> Java 23+
> Maven
> Chrome y Firefox instalados
> Drivers `chromedriver` y `geckodriver` descargados en la carpeta `/drivers`

Links de descarga de drivers

> Chrome: https://chromedriver.chromium.org/
> Firefox: https://github.com/mozilla/geckodriver/releases

Cómo ejecutar
> Ejecutar en Chrome (por default)
>> mvn test

> Ejecutar en Firefox
>> mvn test -Dbrowser=firefox

Como visualizar los reportes
>> Luego de la ejecución se genera un reporte en target/surefire-reports/index.html
>> Clickeando en la opción "Reporter output" del apartado "Info" se puede ver la captura de pantalla de un test fallido

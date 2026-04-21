* Para correr el proyecto ejecutar:
mvn clean install
docker build -t pedidos-api .
docker run -p 8080:8080 pedidos-api

*Endpoint a utilizar:
POST /mensajeria

*Ejemplo de body json para probar el endpoint:

{
	"enviarPedido": {
		"numPedido": "75630275",
		"cantidadPedido": "1",
		"codigoEAN": "00110000765191002104587",
		"nombreProducto": "Armario INVAL",
		"numDocumento": "1113987400",
		"direccion": "CR 72B 45 12 APT 301"
	}
}

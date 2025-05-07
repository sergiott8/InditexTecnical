Feature: Servicio de gestion de productos y ofertas

# Casos Positivos

  Scenario: Se puede pedir un producto que existe
    Given tenemos el producto existente
    When pido un producto: 33455 y con brand: 1 en una fecha determinada "2020-06-15T10:00:00"
    Then obtengo el producto seleccionado
    And el codigo de respuesta es 200


# Casos Negativos

  Scenario: No se puede encontrar un producto que no existe
    Given tenemos el producto existente
    When pido un producto no existente: 8340235 y con brand: 1 en una fecha determinada "2020-06-15T10:00:00"
    Then el codigo de respuesta es 404


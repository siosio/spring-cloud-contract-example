import org.springframework.cloud.contract.spec.Contract

Contract.make {
  priority 2
  request {
    method('GET')
    url "/sample/${regex('\\d+')}"
  }

  response {
    status(OK())

    headers {
      contentType(applicationJson())
    }
    
    body(
        id: fromRequest().path(1),
        name: "name_${fromRequest().path(1)}"
    )
  }
}
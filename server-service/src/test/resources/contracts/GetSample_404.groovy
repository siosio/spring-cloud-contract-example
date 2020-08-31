import org.springframework.cloud.contract.spec.Contract

Contract.make {
  priority 1
  request {
    method('GET')
    url ("/sample/999")
  }

  response {
    status(NOT_FOUND())
  }
}

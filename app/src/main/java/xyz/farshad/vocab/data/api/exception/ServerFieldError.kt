package xyz.farshad.vocab.data.api.exception

data class ServerFieldError(val objectName: String, val field: String, val message: String)
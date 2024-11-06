package de.dojodev.mybiblenotes.bibleapi.requests.base

enum class ResponseCode {
    NoRequest,
    Successful,
    NotAuthorizedOrInvalidLanguageCode,
    UnAuthorized,
    NotAuthorized,
    NotFound,
    Unknown
}
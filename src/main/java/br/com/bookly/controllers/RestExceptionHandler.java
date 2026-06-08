package br.com.bookly.controllers;


import br.com.bookly.entities.dtos.ExceptionResponseDTO;
import br.com.bookly.exceptions.*;
import jakarta.el.ELException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ExistentBookClubException.class)
    public ResponseEntity<ExceptionResponseDTO> existentBookClubExceptionHandler(ExistentBookClubException ebc, HttpServletRequest request) {
            ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ebc.getMessage(), 400, request.getRequestURI());
            return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(InexistentBookClubException.class)
    public ResponseEntity<ExceptionResponseDTO> inexistentBookClubExceptionHandler(InexistentBookClubException ibc, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ibc.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponseDTO> badRequestExceptionHandler(BadRequestException bre, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(bre.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(ExistingParticipantUserException.class)
    public ResponseEntity<ExceptionResponseDTO> existingParticipantUserExceptionHandler(ExistingParticipantUserException epu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(epu.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(InexistentParticipantUserException.class)
    public ResponseEntity<ExceptionResponseDTO> inexistentParticipantUserExceptionHandler(InexistentParticipantUserException ipu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(ExistentBookClubAssignmentException.class)
    public ResponseEntity<ExceptionResponseDTO> existentBookClubAssignmentExceptionHandler(ExistentBookClubAssignmentException ebca, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ebca.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(InexistentBookClubAssignmentException.class)
    public ResponseEntity<ExceptionResponseDTO> inexistentBookClubAssignmentExceptionHandler(InexistentBookClubAssignmentException ibca, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ibca.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(invalidDateException.class)
    public ResponseEntity<ExceptionResponseDTO> invalidDateBookClubAssignmentExceptionHandler(invalidDateException idbca, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(idbca.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(InexistentClubMessageException.class)
    public ResponseEntity<ExceptionResponseDTO> inexistentClubMessageExceptionHandler(InexistentClubMessageException icm, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(icm.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(NotModifiedClubMessageException.class)
    public ResponseEntity<ExceptionResponseDTO> notModifiedClubMessageExceptionHandler(NotModifiedClubMessageException nmcm, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(nmcm.getMessage(), 409, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }


    @ExceptionHandler(InexistentAuthorException.class)
    public ResponseEntity<ExceptionResponseDTO> inexistentAuthorExceptionHandler(InexistentAuthorException ipu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(ExistingColectionByUserException.class)
    public ResponseEntity<ExceptionResponseDTO> existingColectionByUserExceptionHandler(ExistingColectionByUserException epu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(epu.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(InexistentIdUserException.class)
    public ResponseEntity<ExceptionResponseDTO> inexistentIdUserExceptionHandler(InexistentIdUserException ipu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler (InexistentColectionException.class)
    public ResponseEntity<ExceptionResponseDTO> inexistentColectionExceptionHandler(InexistentColectionException ipu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler (InexistentBookException.class)
    public ResponseEntity<ExceptionResponseDTO> InexistentBookExceptionHandler(InexistentBookException ipu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler (InvalidBookDataException.class)
    public ResponseEntity<ExceptionResponseDTO> InvalidBookDataExceptionHandler(InvalidBookDataException ipu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler (InvalidReadingStatusException.class)
    public ResponseEntity<ExceptionResponseDTO> InvalidReadingStatusExceptionHandler(InvalidReadingStatusException ipu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler (ReadingStatusNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> ReadingStatusNotFoundExceptionHandler(ReadingStatusNotFoundException ipu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(DifferentException.class)
    public ResponseEntity<ExceptionResponseDTO> DifferentExceptionHandler(DifferentException ipu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(ExistentEmailException.class)
    public ResponseEntity<ExceptionResponseDTO> ExistentEmailExceptionHandler(ExistentEmailException ipu, HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(InexistentEmailException.class)
    public ResponseEntity<ExceptionResponseDTO> InexistentEmailExceptionHandler(InexistentEmailException ipu, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }


    @ExceptionHandler(InexistentPurchaseException.class)
    public ResponseEntity<ExceptionResponseDTO> InexistentPurchaseExceptionHandler(InexistentPurchaseException ipu, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler (InexistentPurchaseBookException.class)
    public ResponseEntity<ExceptionResponseDTO> InexistentPurchaseBookExceptionHandler(InexistentPurchaseBookException ipu, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler (InexistentRatingException.class)
    public ResponseEntity<ExceptionResponseDTO> InexistentRatingExceptionHandler(InexistentRatingException ipu, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(InexistentUserByEmailException.class)
    public ResponseEntity<ExceptionResponseDTO> InexistentUserExceptionHandler(InexistentUserByEmailException ipu, HttpServletRequest request){
        ExceptionResponseDTO  exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(InvalidRatingException.class)
    public ResponseEntity<ExceptionResponseDTO> InvalidRatingExceptionHandler(InvalidRatingException ipu, HttpServletRequest request){
        ExceptionResponseDTO  exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(ExistentAuthorException.class)
    public ResponseEntity<ExceptionResponseDTO> ExistentAuthorExceptionHandler(ExistentAuthorException ipu, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ipu.getMessage(), 404, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ExceptionResponseDTO> InvalidTokenExceptionHandler(InvalidTokenException ite, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ite.getMessage(), 401, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(TokenGenerationException.class)
    public ResponseEntity<ExceptionResponseDTO> TokenGenerationExceptionHandler(TokenGenerationException tge, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(tge.getMessage(), 500, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatusCode()).body(exceptionResponseDTO);
    }

    //erro generico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDTO> handleGeneralException(Exception ex, HttpServletRequest request) {
        ExceptionResponseDTO error = new ExceptionResponseDTO(ex.getMessage(), 500, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}

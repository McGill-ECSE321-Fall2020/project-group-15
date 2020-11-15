<template>
    <div>
        <head>
        <title>Update Profile</title>
        </head>
        <body>
            <div class="navbarContainer">
                <Navbar navMode="false" />
            </div>
            <div class="card-container">
                <div class="card w-75">
                    <div class="card-body">
                        <form>
                            <div class="form-group">
                                <h4>First Name: </h4>
                                <div v-if="!fields.editFirstName" class="non-edit-container">
                                    <p class="setting-text-style">{{this.userData.firstName}}</p>
                                    <button type="submit" class="btn btn-primary edit-button-style" @click="editField('editFirstName')">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                </div>
                                <div v-else class="edit-container">
                                    <input type="text" class="form-control" v-model="fields.firstName">
                                    <div class="edit-btn-container">
                                        <button type="button" class="btn btn-success edit-button-style" @click="submitEdit('firstName', fields.firstName, false)">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                            </svg>
                                        </button>
                                        <button type="button" class="btn btn-danger edit-button-style" @click="cancelEdit()">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                                <div v-if="fields.errorFirstName" class="alert alert-danger" role="alert">
                                    {{error}}
                                </div>
                                <hr>
                            </div>
                            <div class="form-group">
                                <h4>Last Name: </h4>
                                <div v-if="!fields.editLastName" class="non-edit-container">
                                    <p class="setting-text-style">{{this.userData.lastName}}</p>
                                    <button type="submit" class="btn btn-primary edit-button-style" @click="editField('editLastName')">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                </div>
                                <div v-else class="edit-container">
                                    <input type="text" class="form-control" v-model="fields.lastName">
                                    <div class="edit-btn-container">
                                        <button type="button" class="btn btn-success edit-button-style" @click="submitEdit('lastName', fields.lastName, false)">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                            </svg>
                                        </button>
                                        <button type="button" class="btn btn-danger edit-button-style" @click="cancelEdit()">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                                <div v-if="fields.errorLastName" class="alert alert-danger" role="alert">
                                    {{error}}
                                </div>
                                <hr>
                            </div>
                            <div class="form-group">
                                <h4>Email: </h4>
                                <div v-if="!fields.editEmail" class="non-edit-container">
                                    <p class="setting-text-style">{{this.userData.email}}</p>
                                    <button type="submit" class="btn btn-primary edit-button-style" @click="editField('editEmail')">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                </div>
                                <div v-else class="edit-container">
                                    <input type="text" class="form-control" v-model="fields.email">
                                    <div class="edit-btn-container">
                                        <button type="button" class="btn btn-success edit-button-style" @click="submitEdit('email', fields.email, false)">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                            </svg>
                                        </button>
                                        <button type="button" class="btn btn-danger edit-button-style" @click="cancelEdit()">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                                <div v-if="fields.errorEmail" class="alert alert-danger" role="alert">
                                    {{error}}
                                </div>
                                <hr>
                            </div>
                            <div class="form-group">
                                <div v-if="!fields.editPassword" class="non-edit-container">
                                    <h4>Password</h4>
                                    <button type="submit" class="btn btn-primary edit-button-style" @click="editField('editPassword')">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                </div>
                                <div v-else class="edit-password-container">
                                    <div class="edit-new-password-text-container">
                                        <h4>New Password</h4>
                                        <input type="password" class="form-control" v-model="fields.password" @change="checkPasswordMatch()">
                                    </div>
                                    <div class="edit-reenter-password-text-container">
                                        <h4>Re-enter Password</h4>
                                        <div class="edit-container">
                                            <input type="password" class="form-control" v-model="fields.reenterPassword" @change="checkPasswordMatch()">
                                            <div class="edit-btn-container">
                                                <button type="button" class="btn btn-success edit-button-style" @click="submitEdit('password', fields.password, false)">
                                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                        <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                                    </svg>
                                                </button>
                                                <button type="button" class="btn btn-danger edit-button-style" @click="cancelEdit()">
                                                    <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                        <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                                    </svg>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div v-if="fields.errorPassword" class="alert alert-danger" role="alert">
                                    {{error}}
                                </div>
                                <hr>
                            </div>

                            <div class="form-group">
                                <h4>Phone Number: </h4>
                                <div v-if="!fields.editPhoneNumber" class="non-edit-container">
                                    <p class="setting-text-style">{{this.userData.phoneNumber}}</p>
                                    <button type="submit" class="btn btn-primary edit-button-style" @click="editField('editPhoneNumber')">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                </div>
                                <div v-else class="edit-container">
                                    <input type="text" class="form-control" v-model="fields.phoneNumber">
                                    <div class="edit-btn-container">
                                        <button type="button" class="btn btn-success edit-button-style" @click="submitEdit('phoneNumber', fields.phoneNumber, false)">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                            </svg>
                                        </button>
                                        <button type="button" class="btn btn-danger edit-button-style" @click="cancelEdit()">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                                <div v-if="fields.errorPhoneNumber" class="alert alert-danger" role="alert">
                                    {{error}}
                                </div>
                                <hr>
                            </div>
                            <div class="form-group">
                                <h4>Address Line 1: </h4>
                                <div v-if="!fields.editAddressLine1" class="non-edit-container">
                                    <p class="setting-text-style">{{this.userData.address.addressLine1}}</p>
                                    <button type="submit" class="btn btn-primary edit-button-style" @click="editField('editAddressLine1')">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                </div>
                                <div v-else class="edit-container">
                                    <input type="text" class="form-control" v-model="fields.addressLine1">
                                    <div class="edit-btn-container">
                                        <button type="button" class="btn btn-success edit-button-style" @click="submitEdit('addressLine1', fields.addressLine1, true)">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                            </svg>
                                        </button>
                                        <button type="button" class="btn btn-danger edit-button-style" @click="cancelEdit()">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                                <div v-if="fields.errorAddressLine1" class="alert alert-danger" role="alert">
                                    {{error}}
                                </div>
                                <hr>
                            </div>
                            <div class="form-group">
                                <h4>Address Line 2: </h4>
                                <div v-if="!fields.editAddressLine2" class="non-edit-container">
                                    <p class="setting-text-style">{{this.userData.address.addressLine2}}</p>
                                    <button type="submit" class="btn btn-primary edit-button-style" @click="editField('editAddressLine2')">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                </div>
                                <div v-else class="edit-container">
                                    <input type="text" class="form-control" v-model="fields.addressLine2">
                                    <div class="edit-btn-container">
                                        <button type="button" class="btn btn-success edit-button-style" @click="submitEdit('addressLine2', fields.addressLine2, true)">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                            </svg>
                                        </button>
                                        <button type="button" class="btn btn-danger edit-button-style" @click="cancelEdit()">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                                <div v-if="fields.errorAddressLine2" class="alert alert-danger" role="alert">
                                    {{error}}
                                </div>
                                <hr>
                            </div>
                            <div class="form-group">
                                <h4>City: </h4>
                                <div v-if="!fields.editCity" class="non-edit-container">
                                    <p class="setting-text-style">{{this.userData.address.city}}</p>
                                    <button type="submit" class="btn btn-primary edit-button-style" @click="editField('editCity')">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                </div>
                                <div v-else class="edit-container">
                                    <input type="text" class="form-control" v-model="fields.city">
                                    <div class="edit-btn-container">
                                        <button type="button" class="btn btn-success edit-button-style" @click="submitEdit('city', fields.city, true)">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                            </svg>
                                        </button>
                                        <button type="button" class="btn btn-danger edit-button-style" @click="cancelEdit()">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                                <div v-if="fields.errorCity" class="alert alert-danger" role="alert">
                                    {{error}}
                                </div>
                                <hr>
                            </div>
                            <div class="form-group">
                                <h4>Province: </h4>
                                <div v-if="!fields.editProvince" class="non-edit-container">
                                    <p class="setting-text-style">{{this.userData.address.province}}</p>
                                    <button type="submit" class="btn btn-primary edit-button-style" @click="editField('editProvince')">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                </div>
                                <div v-else class="edit-container">
                                    <input type="text" class="form-control" v-model="fields.province">
                                    <div class="edit-btn-container">
                                        <button type="button" class="btn btn-success edit-button-style" @click="submitEdit('province', fields.province, true)">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                            </svg>
                                        </button>
                                        <button type="button" class="btn btn-danger edit-button-style" @click="cancelEdit()">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                                <div v-if="fields.errorProvince" class="alert alert-danger" role="alert">
                                    {{error}}
                                </div>
                                <hr>
                            </div>
                            <div class="form-group">
                                <h4>Postal Code: </h4>
                                <div v-if="!fields.editPostalCode" class="non-edit-container">
                                    <p class="setting-text-style">{{this.userData.address.postalCode}}</p>
                                    <button type="submit" class="btn btn-primary edit-button-style" @click="editField('editPostalCode')">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                </div>
                                <div v-else class="edit-container">
                                    <input type="text" class="form-control" v-model="fields.postalCode">
                                    <div class="edit-btn-container">
                                        <button type="button" class="btn btn-success edit-button-style"  @click="submitEdit('postalCode', fields.postalCode, true)">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                            </svg>
                                        </button>
                                        <button type="button" class="btn btn-danger edit-button-style" @click="cancelEdit()">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                                <div v-if="fields.errorPostalCode" class="alert alert-danger" role="alert">
                                    {{error}}
                                </div>
                                <hr>
                            </div>
                            <div class="form-group">
                                <h4>Country: </h4>
                                <div v-if="!fields.editCountry" class="non-edit-container">
                                    <p class="setting-text-style">{{this.userData.address.country}}</p>
                                    <button type="submit" class="btn btn-primary edit-button-style" @click="editField('editCountry')">
                                        <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-pencil" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                            <path fill-rule="evenodd" d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5L13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175l-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
                                        </svg>
                                    </button>
                                </div>
                                <div v-else class="edit-container">
                                    <input type="text" class="form-control" v-model="fields.country">
                                    <div class="edit-btn-container">
                                        <button type="button" class="btn btn-success edit-button-style" @click="submitEdit('country', fields.country, true)">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-check" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M10.97 4.97a.75.75 0 0 1 1.071 1.05l-3.992 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.236.236 0 0 1 .02-.022z"/>
                                            </svg>
                                        </button>
                                        <button type="button" class="btn btn-danger edit-button-style" @click="cancelEdit()">
                                            <svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-x" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                                            </svg>
                                        </button>
                                    </div>
                                </div>
                                <div v-if="fields.errorCountry" class="alert alert-danger" role="alert">
                                    {{error}}
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </body>
    </div>
</template>

<script>
import axios from 'axios';
import Navbar from '@/components/Navbar';
import { mapActions, mapGetters } from 'vuex';

var config = require('../../config')

var backendConfigurer = function(){
    switch(process.env.NODE_ENV){
        case 'development':
            return 'http://' + config.dev.backendHost + ':' + config.dev.backendPort;
        case 'production':
            return 'https://' + config.build.backendHost + ':' + config.build.backendPort ;
    }
};

var frontendConfigurer = function(){
    switch(process.env.NODE_ENV){
        case 'development':
            return 'http://' + config.dev.host + ':' + config.dev.port;
        case 'production':
            return 'https://' + config.build.host + ':' + config.build.port ;
    }
};
var backendUrl = backendConfigurer();
var frontendUrl = frontendConfigurer();

var AXIOS = axios.create({
    baseURL: backendUrl,
    headers: { 'Access-Control-Allow-Origin': frontendUrl }
})



export default {
    name: 'customerSettings',
    components: {
        Navbar
    },
    computed: mapGetters(['userData']),
    data () {
        return {
            fields: {
                firstName: 'test',
                lastName: '',
                email: '',
                userID: '',
                password: '',
                reenterPassword: '',
                phoneNumber: '',
                addressLine1: '',
                addressLine2: '',
                city: '',
                province: '',
                postalCode: '',
                country: '',
                
                editFirstName: false,
                editLastName: false,
                editEmail: false,
                editUserID: false,
                editPassword: false,
                editPhoneNumber: false,
                editAddressLine1: false,
                editAddressLine2: false,
                editCity: false,
                editProvince: false,
                editPostalCode: false,
                editCountry: false,
                
                errorFirstName: false,
                errorLastName: false,
                errorEmail: false,
                errorUserID: false,
                errorPassword: false,
                errorPhoneNumber: false,
                errorAddressLine1: false,
                errorAddressLine2: false,
                errorCity: false,
                errorProvince: false,
                errorPostalCode: false,
                errorCountry: false,
            },
            error: ''
        }
    },
    methods: {
        ...mapActions(['setUserData']),
        editField: function(fieldName) {
            this.resetFields();
            this.fields[fieldName] = true;
        },
        async submitEdit(fieldName, fieldValue, isAddressField) {
            if(fieldName == "phoneNumber"){
                this.checkPhoneNumber();
            }
            if(this.fields.errorPassword || this.fields.errorPhoneNumber) {
                return;
            }
            var customerDTO = this.getCustomerDTO();
            if(!isAddressField) {
                customerDTO[fieldName] = fieldValue
                console.log(customerDTO)
                await AXIOS.put("/customers", customerDTO)
                    .then(response => {
                        console.log(response);
                        this.setUserData(response.data);
                        this.resetFields();
                    })
                    .catch(e => {
                        console.log(e);
                        var errorString = "error" + fieldName.charAt(0).toUpperCase() + fieldName.slice(1)
                        this.fields[errorString] = true;
                        // var errorMsg = e.response.data
                        // this.error = errorMsg
                    })
            } else if(isAddressField){
                customerDTO[address][fieldName] = fieldValue
                console.log(customerDTO)
            }
        },
        cancelEdit: function() {
            this.resetFields();
        },
        getCustomerDTO() {
            var dto = {
                "userID" : this.userData.userID,
                "email" : this.userData.email,
                "password" : this.userData.password,
                "firstName" : this.userData.firstName,
                "lastName" : this.userData.lastName,
                "phoneNumber" : this.userData.phoneNumber,
                "address" : {
                    "addressLine1" : this.userData.address.addressLine1,
                    "addressLine2" : this.userData.address.addressLine2,
                    "city" : this.userData.address.city,
                    "province" : this.userData.address.province,
                    "postalCode" : this.userData.address.postalCode,
                    "country" : this.userData.address.country,
                }
            }
            return dto;
        },
        resetFields() {
            this.fields.firstName = '';
            this.fields.lastName = '';
            this.fields.email = '';
            this.fields.userID = '';
            this.fields.password = '';
            this.fields.reenterPassword = '';
            this.fields.phoneNumber = '';
            this.fields.addressLine1 = '';
            this.fields.addressLine2 = '';
            this.fields.city = '';
            this.fields.province = '';
            this.fields.postalCode = '';
            this.fields.country = '';
            
            this.fields.editFirstName = false;
            this.fields.editLastName = false;
            this.fields.editEmail = false;
            this.fields.editUserID = false;
            this.fields.editPassword = false;
            this.fields.editPhoneNumber = false;
            this.fields.editAddressLine1 = false;
            this.fields.editAddressLine2 = false;
            this.fields.editCity = false;
            this.fields.editProvince = false;
            this.fields.editPostalCode = false;
            this.fields.editCountry = false;
            
            this.fields.errorFirstName = false;
            this.fields.errorLastName = false;
            this.fields.errorEmail = false;
            this.fields.errorUserID = false;
            this.fields.errorPassword = false;
            this.fields.errorPhoneNumber = false;
            this.fields.errorAddressLine1 = false;
            this.fields.errorAddressLine2 = false;
            this.fields.errorCity = false;
            this.fields.errorProvince = false;
            this.fields.errorPostalCode = false;
            this.fields.errorCountry = false;

            this.error = '';
        },
        checkPasswordMatch: function() {
            if(this.fields.password != this.fields.reenterPassword){
                this.error = "Passwords do not match";
                this.fields.errorPassword = true;
            } else {
                this.fields.errorPassword = false;
                this.error = '';
            }
        },
        checkPhoneNumber: function() {
            var isPhoneNumber = (/^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$/im.test(this.fields.phoneNumber))
            if(!isPhoneNumber){
                this.error = "Invalid phone number";
                this.fields.errorPhoneNumber = true;
            } else {
                this.fields.errorPhoneNumber = false;
                this.error = "";
            }
        },
    },
    created() {
        console.log(this.userData)
        console.log(this.userData.firstName)
    }
}
    
</script>

<style>
    .card-container {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-bottom: 50px;
    }
    .row {
        margin-bottom: 10px;
    }
    .button-container {
      width: 100%;
    }
    .signin-btn-style {
        color: #037BFF;
    }
    .signin-btn-style:hover {
        color: #037BFF;
    }
    .button-container {
        display: flex;
        justify-content: space-around;
    }
    .edit-button-style {
        height: 40px;
        width: 40px;
        margin-left: 5px;
    }
    .navbarContainer {
        margin-bottom: 100px;
    }
    .non-edit-container {
        display: flex;
        justify-content: space-between;
    }
    .edit-container {
        display: flex;
        justify-content: space-between;
    }
    .setting-text-style {
        margin-top: 10px;
        font-size: 20px;
    }
    .edit-btn-container {
        display: flex;
    }
    .edit-new-password-text-container {
        margin-bottom: 10px;
    }
</style>
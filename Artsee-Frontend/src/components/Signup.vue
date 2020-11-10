<template>
    <div class="signup-container">
        <div class="card w-25">
            <div class="card-body">
                <img class="imageStyle" src="@/assets/logo.png">
                <form>
                    <div class="row">
                        <div class="col">
                            <input type="text" class="form-control" placeholder="First name">
                        </div>
                        <div class="col">
                            <input type="text" class="form-control" placeholder="Last name">
                        </div>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Email Address">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Re-enter Password">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Address Line 1">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Address Line 2">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="City">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Province">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Postal Code">
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Country">
                    </div>
                    <div class="row">
                        <div class="col">
                            <button type="submit" class="btn btn-light signinBtn">Sign In Instead</button>
                        </div>
                        <div class="col">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>

</script>

<style>
    .signup-container {
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .row {
        margin-bottom: 10px;
    }
    .imageStyle {
        width: 60%;
    }
    .signinBtn {
        color: #037BFF;
    }
    .signinBtn:hover {
        color: #037BFF;
    }
</style>
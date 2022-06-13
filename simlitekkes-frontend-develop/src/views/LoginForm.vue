<template>
  <v-content class="login-content">
    <v-img src="../assets/bg.png" class="background-login"/>
      <v-container dark
        fill-height
        fluid
        class="container-login">
      <v-layout
        align-center
        justify-center
        >
        <v-flex
          xs10
          sm8
          md4>
          <v-img src="../assets/avatar.png" class="avatar-login" />
          <v-card 
            flat
            class="elevation-12 card-login">
              <p class="title-user-login text-uppercase text-center font-weight-black">
                User Login
              </p>

            <v-card-text>
              <v-form>
                <v-text-field
                  id="mxUsername"
                  class="mt-2 ml-10 mr-10"
                  ref="username"
                  v-model="username"
                  :class="{mx_empty_filed: !username}"
                  :rules="[() => !!username || 'This field is required']"
                  prepend-inner-icon="mdi-account"
                  label="Username"
                  placeholder="Username"
                  required
                  outlined
                />
                <v-text-field
                  id="mxPassword"
                  class="ml-10 mr-10"
                  ref="password"
                  v-model="password"
                  :class="{mx_empty_filed: !password}"
                  :rules="[() => !!password || 'This field is required']"
                  :append-icon="showPassword ? 'mdi-eye-off' : 'mdi-eye'"
                  :type="showPassword ? 'text' : 'password'"
                  prepend-inner-icon="mdi-lock"
                  label="Password"
                  placeholder="Password"
                  counter
                  required
                  outlined
                  @keydown.enter="login"
                  @click:append="showPassword = !showPassword"
                />
              </v-form>
            </v-card-text>


            <!-- <div class="recaptcha">
              <template>
                  <div>
                    <vue-recaptcha
                      ref="recaptcha"
                      @verify="onVerify"
                      @expired="onExpired"
                      :sitekey="sitekey">
                    </vue-recaptcha>
                  </div>
              </template>
            </div> -->
            <div class="text-center pt-2">
              <template>
                <!-- <vue-recaptcha sitekey="6LeGVgEVAAAAAI0_xZPUIysLsSsCk3IAPUFkuZP8"> -->
                  <v-btn 
                    rounded
                    x-large
                    class="button-login font-weight-bold"
                    :loading="loading"
                    @click="login()"                     
                    >Login
                  </v-btn>
              </template>
            </div>
            <div class="text-center pb-10">
              <template>
                  <v-btn  
                    text 
                    @click="forgotPassword()"
                  >Forgot Password?
                </v-btn>
              </template>
            </div>
            <v-snackbar
              v-model="snackbar"
              :color="color"
              :top='true'
            >
              {{ errorMessages }}
              <v-btn
                dark
                @click="snackbar = false"
              >
                Close
              </v-btn>
            </v-snackbar>
          </v-card>
        </v-flex>
      </v-layout>

    </v-container>
  </v-content>
</template>

<script>
import VueRecaptcha from 'vue-recaptcha'

export default {
  components: { 
    VueRecaptcha, 
    },
  data: function () {
    return {
      username: '',
      password: '',
      sitekey: '6LdXtKQZAAAAABq6xKaAhPbyYrjQHQnvx9owa0vy',
      errorMessages: 'Incorrect login info',
      snackbar: false,
      color: 'primary',
      showPassword: false,
      loading: false
    }
  },

  methods: {
    onVerify: function (response) {
      this.recaptcha = response
      console.log('Verify: ' + response)
    },
    onExpired: function () {
      console.log('Expired')
    },
    resetRecaptcha () {
      this.$refs.recaptcha.reset() // Direct call reset method
    },
    forgotPassword() {
      this.$router.push('/forgot-password')
    },
		login: function () {
			if ( 
        this.username 
        && this.password 
        // && this.recaptcha 
        ) {
        let username = this.username
        let password = this.password
				this.loading=true
        console.log("debug : "+username);
        this.$store.dispatch('login', { username, password })
          .then(() => this.$router.push('/home'))
          .catch(err => {
          this.loading=false
          console.log(err)
          this.snackbar= true
          }
          )
			}
		}
  },
  metaInfo () {
    return {
      title: 'SIMLITEKKES | Login'
    }
  }
}
</script>


<style scoped>
  .background-login{
    position: fixed;
    height: 100%;
    left: 0;
    bottom: 0;
    z-index: 1;
  }

  .container-login{
    margin-left: 25%;
    margin-top: -3%;
  }

  .recaptcha {
    margin-top: -4%;
    margin-left: 25%;
  }

  .card-login{
    z-index: 1;
  }

  .avatar-login{
    width: 40%;
    z-index: 2;
    margin-bottom: -15%;
    align-content: center;
    margin-left: 28%;
  }

  .title-user-login{
    padding-top: 15%;
    margin-bottom: -10px;
    color: #393939;
    font-size: 50px;
  }

  .button-login{
    width: 40%;
    height: 50px;
    border-radius: 25px;
    font-size: 1.5rem;
    outline: none;
    border:none;
    text-transform: uppercase;
    background-image: linear-gradient(to right, #50B2AF,#51989B, #537F87);
    cursor: pointer;
    color: #fff;
    font-family: 'Poppins',sans-serif;
    background-size: 200%;
    transition: .5s;
  }

  .button-login:hover{
    background-position: right;
  }

  .custom-loader {
    animation: loader 1s infinite;
    display: flex;
  }

  @-moz-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-webkit-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @-o-keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
  @keyframes loader {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }


  @media screen and (max-width: 1200px){
    .title-user-login{
      padding-top: 15%;
      margin-bottom: -10px;
      font-size: 35px;
    }
  }

  @media screen and (max-width: 1050px){
    .container-login{
      margin-left: 0%;
    }
    .recaptcha {
      margin-left: 6%;
    }
    .background-login{
      display: none;
    }
    .login-content{
      background-image: linear-gradient(to right, #50B2AF,#51989B, #537F87);
    }
  }

  @media screen and (max-width: 960px){
    .container-login{
      margin-left: 0%;
    }
    .recaptcha {
      margin-left: 25%;
    }
    .avatar-login{
      width: 40%;
      height: 40%;
      margin-left: 30%;
    }
  }

  @media screen and (max-width: 600px){
    .container-login{
      margin-left: 0%;
    }
    .background-login{
      display: none;
    }
    .recaptcha {
      margin-left: 15%;
    }
    .avatar-login{
      width: 45%;
      height: 45%;
      margin-left: 27%;
      margin-bottom: -15%;
    }
  }

    @media screen and (max-width: 400px){
    .container-login{
      margin-left: 0%;
    }
    .background-login{
      display: none;
    }
    .recaptcha {
      margin-left: 1%;
    }
    .avatar-login{
      width: 45%;
      height: 45%;
      margin-left: 25%;
      margin-bottom: -15%;
    }
  }

</style>

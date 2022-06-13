<template>
  <v-content class="forgot-password-content">
    <v-img src="../assets/bg.png" class="background-forgot-password"/>
      <v-container dark
        fill-height
        fluid
        class="container-forgot-password">
      <v-layout
        align-center
        justify-center>
        <v-flex
          xs10
          sm8
          md4>
          <v-card flat
            class="elevation-5 card-forgot-password">
              <p class="title-forgot-password text-uppercase text-center font-weight-black">
                Reset Password
              </p>

            <v-card-text>
              <v-form ref="form" v-if="isFormValid" >
                <v-text-field
                  class="mt-2 ml-10 mr-10"
                  ref="email"
                  v-model="defaultItem.email"
                  :rules="[() => !!defaultItem.email || 'This field is required']"
                  prepend-inner-icon="mdi-email"
                  label="Email"
                  placeholder="Email"
                  required
                  outlined
                />
              </v-form>
              <p v-if="!isFormValid" class="text-center"> 
                Silahkan cek email anda, anda akan menerima username beserta password baru 
              </p>

              <p v-if="!isFormValid" class="text-center"> 
                <v-btn color="success"
                    v-if="!isFormValid"
                    @click="back()"
                    >Kembali ke Halaman Login
                </v-btn>
              </p>
    
            </v-card-text>

            <div class="recaptcha">
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
            </div>

            <!-- {{defaultItem}} -->

            <p class="text-center pb-10 pt-2">
              <template>
                <v-btn 
                  rounded
                  x-large
                  class="button-forgot-password font-weight-bold"
                  v-if="isFormValid"
                  :loading="loadingForgot"
                  @click="save()"
                  >Reset Password
                </v-btn>
              </template>
            </p>
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
import VueRecaptcha from 'vue-recaptcha';
import {apiServer} from "../setup-axios"

export default {
  components: { VueRecaptcha },
  data: function () {
    return {
      password: '',
      sitekey: '6LdXtKQZAAAAABq6xKaAhPbyYrjQHQnvx9owa0vy',
      errorMessages: 'Incorrect login info',
      snackbar: false,
      color: 'primary',
      showPassword: false,
      isFormValid: true,
      loadingForgot: false,
      defaultItem: {
        email: "",
        isValid: "",
      }
    }
  },

  // Sends action to Vuex that will log you in and redirect to the dash otherwise, error
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
    login: function () {
			if( this.username && this.password && this.recaptcha ) {
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
		},
    back(){
      this.$router.push('/')
    },
    save() {
      apiServer
        .put("/userweb/forgot-password", this.defaultItem)
        .then(response => {
          console.log(response.data)
          if(response.data.isValid) {
            this.loadingForgot = true
            this.isFormValid = false
            this.errorMessages = "reset password success"
            this.snackbar = true
          } else {
            this.loadingForgot=false
            this.errorMessages = "email invalid"
            this.snackbar = true
          }
        })
        .catch(error => {
          console.log("There was an error:", error.response); // Logs out the error
        });
    },
  },
  metaInfo () {
    return {
      title: 'Lupa Password'
    }
  }
}
</script>


<style scoped>
  .background-forgot-password{
    position: fixed;
    height: 100%;
    left: 0;
    bottom: 0;
    z-index: 1;
  }

  .recaptcha {
    margin-top: -4%;
    margin-left: 25%;
  }

  .container-forgot-password{
    margin-left: 25%;
    margin-top: -3%;
  }

  .card-forgot-password{
    z-index: 1;
  }

  .title-forgot-password{
    padding-top: 5%;
    margin-bottom: -5px;
    color: #393939;
    font-size: 50px;
  }

  .button-forgot-password{
    width: 60%;
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

  .button-forgot-password:hover{
    background-position: right;
  }

  @media screen and (max-width: 1200px){
    .title-forgot-password{
      padding-top: 15%;
      margin-bottom: -10px;
      font-size: 35px;
    }
  }

  @media screen and (max-width: 1050px){
    .container-forgot-password{
      margin-left: 0%;
    }
    .background-forgot-password{
      display: none;
    }
    .forgot-password-content{
      background-image: linear-gradient(to right, #50B2AF,#51989B, #537F87);
    }
  }

  @media screen and (max-width: 960px){
    .container-forgot-password{
      margin-left: 0%;
    }
  }

  @media screen and (max-width: 600px){
    .container-forgot-password{
      margin-left: 0%;
    }
    .background-forgot-password{
      display: none;
    }
  }

    @media screen and (max-width: 400px){
    .container-forgot-password{
      margin-left: 0%;
    }
    .background-forgot-password{
      display: none;
      }
  }

</style>

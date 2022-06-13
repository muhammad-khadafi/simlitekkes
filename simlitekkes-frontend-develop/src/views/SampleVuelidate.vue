 <template>
      <div>
        <input type="email" placeholder="What's your email" v-model="email"/>
         <p v-if="!$v.email.email">Please enter a valid email address.</p>
          <p v-if="!$v.email.required">Email is required.</p>
        <button type="submit">Submit!</button>
      </div>
    </template>
    
    <script>
    import { required, email } from 'vuelidate/lib/validators'
    
    export default {
      data() {
        return {
          email: null
        }
      },
      validations: {
        email: {
          required,
          email
        }
      }
    }
    </script>
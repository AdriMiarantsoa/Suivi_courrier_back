package adri.suivi_courrier.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class JwtAuthentificationResponse {
        private String token;

        public JwtAuthentificationResponse() {
        }
    
        // Getter for token
        public String getToken() {
            return token;
        }
    
        // Setter for token
        public void setToken(String token) {
            this.token = token;
        }    
}

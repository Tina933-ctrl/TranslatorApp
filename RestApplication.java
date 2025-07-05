package translate;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

    @ApplicationPath("/api")
    public class RestApplication extends Application {
        // nu trebuie să conțină nimic; activează scanning pentru JAX-RS
    }




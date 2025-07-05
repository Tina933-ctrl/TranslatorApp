package translate;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Path("/translate")
@Produces(MediaType.APPLICATION_JSON)
public class TranslationResource {

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public Response ping() {
        return Response.ok("API ok").build();
    }



    @GET
    public Response translate(
            @QueryParam("word") String word,
            @QueryParam("src")  @DefaultValue("english") String src,
            @QueryParam("tgt")  @DefaultValue("italian") String tgt
    ) {
        if (word == null || word.isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Collections.singletonMap("error", "Parametrul 'word' e obligatoriu"))
                    .build();
        }

        Set<String> supportedLanguages = Set.of("english", "italian", "romanian", "spanish", "german", "french");
        if (!supportedLanguages.contains(tgt.toLowerCase())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Collections.singletonMap("error", "Limba în care traducerea se dorește a fi obținută nu există."))
                    .build();
        }

        Optional<String> rezultatTraducere = DictionaryLoader.translate(word, src, tgt);
        if (rezultatTraducere.isPresent()) {
            return Response.ok(Collections.singletonMap("translation", rezultatTraducere.get())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Collections.singletonMap("error", "Cuvântul nu există"))
                    .build();
        }
    }


}


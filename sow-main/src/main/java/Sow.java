import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import sow.page.Practice1Page;

public class Sow extends WebApplication {
    @Override
    public Class<? extends Page> getHomePage() {
        return Practice1Page.class;
    }
}
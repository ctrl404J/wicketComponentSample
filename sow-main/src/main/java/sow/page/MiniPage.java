package sow.page;

import java.util.Arrays;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;

public class MiniPage extends WebPage {

    private static final long serialVersionUID = 1L;

    private Model<String> modelName = Model.of("");
    private Model<String> modelLevel = Model.of("");
    private Model<String> modelType = new Model<String>(null);
    private Model<Boolean> modelAdmin = Model.of(false);

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form<Void> form;
        add(form = new Form<Void>("form") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit() {
                System.out.println("--mini--");
                System.out.println("name:" + modelName.getObject());
                System.out.println("level:" + modelLevel.getObject());
                System.out.println("type:" + modelType.getObject());
                System.out.println("admin:" + modelAdmin.getObject());
            }
        });

        form.add(new TextField<String>("name", modelName));
        form.add(new DropDownChoice<String>("level", modelLevel, Arrays.asList(new String[] { "1級", "2級", "3級" })));
        WebMarkupContainer type = new RadioGroup<String>("type", modelType);
        form.add(type);
        type.add(new Radio<String>("type1", new Model<String>("役員")));
        type.add(new Radio<String>("type2", new Model<String>("社員")));
        type.add(new Radio<String>("type3", new Model<String>("アルバイト")));
        form.add(new CheckBox("admin", modelAdmin));

        add(new BookmarkablePageLink<Void>("menu", Menu.class));

    }

}

import java.util.Arrays;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;

import sow.page.Menu;

public class HomePage extends WebPage {

    private static final long serialVersionUID = 1L;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new Form<Void>("form") {
            private static final long serialVersionUID = 1L;

            private Model<String> modelName;
            private Model<String> modelLevel;
            private Model<String> modelType;
            private Model<Boolean> modelAdmin;

            @Override
            protected void onInitialize() {
                super.onInitialize();

                this.modelName = new Model<String>();
                this.modelLevel = new Model<String>();
                this.modelType = new Model<String>();
                this.modelAdmin = new Model<Boolean>();

                add(new TextField<String>("name") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void onInitialize() {
                        super.onInitialize();
                        setDefaultModel(modelName);
                    }
                });

                add(new DropDownChoice<String>("level") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void onInitialize() {
                        super.onInitialize();
                        setDefaultModel(modelLevel);
                        setChoices(Arrays.asList(new String[] { "1級", "2級", "3級" }));
                    }
                });

                add(new RadioGroup<String>("type") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void onInitialize() {
                        super.onInitialize();
                        setDefaultModel(modelType);
                        add(new Radio<String>("type1", new Model<String>("役員")));
                        add(new Radio<String>("type2", new Model<String>("社員")));
                        add(new Radio<String>("type3", new Model<String>("アルバイト")));
                    }
                });

                add(new CheckBox("admin") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void onInitialize() {
                        super.onInitialize();
                        setDefaultModel(modelAdmin);
                    }
                });

                add(new Button("submit") {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void onSubmit() {
                        super.onSubmit();

                        System.out.println("name:" + modelName.getObject());
                        System.out.println("level:" + modelLevel.getObject());
                        System.out.println("type:" + modelType.getObject());
                        System.out.println("admin:" + modelAdmin.getObject());
                    }
                });

            }
        });

        add(new BookmarkablePageLink<Void>("menu", Menu.class));

    }

}

//3. Wicket 페이지 (MyPage 클래스)
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropdownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.markup.html.form.ChoiceRenderer;

import java.util.List;

public class MyPage extends WebPage {
    private CodeNamePair selectedPair;  // 선택된 항목을 저장할 필드
    private MyService service = new MyService(); // 서비스 인스턴스 생성

    public MyPage() {
        // 서비스로부터 CodeNamePair 리스트 가져오기
        List<CodeNamePair> codeNamePairs = service.getCodeNameList();

        // Model 설정
        IModel<CodeNamePair> selectedPairModel = new Model<>(null);
        Form<?> form = new Form<>("form");

        // DropdownChoice 설정
        DropdownChoice<CodeNamePair> dropdown = new DropdownChoice<>(
            "dropdown",
            selectedPairModel,
            codeNamePairs,
            new ChoiceRenderer<>("name", "code")
        );
        dropdown.setOutputMarkupId(true); // 드롭다운의 마크업 ID를 설정하여 Ajax 갱신 가능하도록

        form.add(dropdown);

        // 갱신될 컨테이너 설정
        WebMarkupContainer container = new WebMarkupContainer("container");
        container.setOutputMarkupId(true);
        container.add(new Label("selectedCode", new PropertyModel<>(this, "selectedPair.code")));

        form.add(container);

        // AjaxButton 설정
        form.add(new AjaxButton("submit") {
            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                super.onSubmit(target);
                selectedPair = selectedPairModel.getObject();
                // 선택된 코드 출력
                System.out.println("Selected code: " + selectedPair.getCode());
                // 갱신할 컨테이너 추가
                target.add(container);
                target.add(dropdown); // 드롭다운도 갱신 대상에 추가
            }
        });

        add(form);
    }

    // 선택된 항목의 getter 추가
    public CodeNamePair getSelectedPair() {
        return selectedPair;
    }

    public void setSelectedPair(CodeNamePair selectedPair) {
        this.selectedPair = selectedPair;
    }
}

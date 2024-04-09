package sow.page;

import java.util.List;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import PracticeDto.BoardDto;
import service.BoardService;

public class Practice1Page extends WebPage {
	
	private BoardService boardService ;
	
	public Practice1Page(){
		
	}
	
	public Practice1Page(BoardService boardService){
		this.boardService = boardService;
	}

	@Override
	protected void onInitialize() {
	    super.onInitialize();

	    BoardService boardService = new BoardService();
	    List<BoardDto> myList = boardService.getList();

	    final Form<Void> form = new Form<>("form");
	    add(form);

	    // ListView에서 선택된 ListItem을 추적하는 데 사용할 변수
	    final IModel<BoardDto> selectedModel = Model.of();

	    final ListView<BoardDto> listView = new ListView<>("listView", myList) {
	        @Override
	        protected void populateItem(ListItem<BoardDto> item) {
	            final BoardDto dto = item.getModelObject();
	            item.add(new Label("myName", dto.getName()));
	            item.add(new Label("myAge", dto.getAge()));
	            item.add(new Label("myTel", dto.getTel().toString()));

	            // 선택된 ListItem을 선택된 모델에 설정, 오류 뜰수도 있는데, 여기에서 해당 tr의 아이디를 만들도록 하고
	            // 업데이트시 이 아이디로 set하면 된다.
	            item.add(new AjaxEventBehavior("click") {
	                @Override
	                protected void onEvent(AjaxRequestTarget target) {
	                    selectedModel.setObject(dto);
	                }
	            });
	        }
	    };
	    listView.setOutputMarkupId(true);
	    form.add(listView);

	    final TextField<String> nameInput = new TextField<>("nameInput", Model.of(""));
	    final TextField<String> ageInput = new TextField<>("ageInput", Model.of(""));
	    final TextField<String> telInput = new TextField<>("telInput", Model.of(""));

	    form.add(nameInput);
	    form.add(ageInput);
	    form.add(telInput);

	    final AjaxButton updateBtn = new AjaxButton("updateBtn") {
	        @Override
	        protected void onSubmit(AjaxRequestTarget target) {
	            super.onSubmit(target);

	            // 현재 입력된 값을 가져옵니다.
	            String newName = nameInput.getModelObject();
	            String newAge = ageInput.getModelObject();
	            String newTel = telInput.getModelObject();

	            // 선택된 DTO를 가져옵니다.
	            BoardDto selectedDto = selectedModel.getObject();

	            // 수정된 값을 DTO에 반영합니다.
	            selectedDto.setName(newName);
	            selectedDto.setAge(newAge);
	            selectedDto.setTel(newTel);

	            // 변경된 데이터를 업데이트합니다.
	            target.add(listView);
	        }
	    };

	    form.add(updateBtn);
	}

}

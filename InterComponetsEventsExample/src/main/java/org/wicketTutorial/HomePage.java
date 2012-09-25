package org.wicketTutorial;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
		super(parameters);
	
		final WebMarkupContainer containerInTheMiddle = new WebMarkupContainer("containerInTheMiddle"){
			@Override
			public void onEvent(IEvent<?> event) {
				System.out.println("I'm the container in the middle and I received an event.");
				super.onEvent(event);			
			}
		};
		
		WebMarkupContainer innerContainer = new WebMarkupContainer("innerContainer"){
			@Override
			public void onEvent(IEvent<?> event) {
				System.out.println("I'm the inner container and I received an event.");
				super.onEvent(event);			
			}
		};
		
		add(containerInTheMiddle);
		containerInTheMiddle.add(innerContainer);
		
		
		StatelessLink breadthLink = new StatelessLink("breadthLink") {

			@Override
			public void onClick() {
				send(containerInTheMiddle, Broadcast.BREADTH, "Broadcat mode: breadth.");
			}
		};
		add(breadthLink);
		StatelessLink depthLink = new StatelessLink("depthLink") {

			@Override
			public void onClick() {
				send(containerInTheMiddle, Broadcast.DEPTH, "Broadcat mode: depth.");
			}
		};
		add(depthLink);
		StatelessLink bubbleLink = new StatelessLink("bubbleLink") {

			@Override
			public void onClick() {
				send(containerInTheMiddle, Broadcast.BUBBLE, "Broadcat mode: bubble.");
			}
		};
		add(bubbleLink);
		StatelessLink exactLink = new StatelessLink("exactLink") {

			@Override
			public void onClick() {
				send(containerInTheMiddle, Broadcast.EXACT, "Broadcat mode: exact.");
			}
		};
		add(exactLink);
    }
    
    @Override
    public void onEvent(IEvent<?> event) {
    	System.out.println("I'm the page and I received an event.");
    	super.onEvent(event);
    }
}

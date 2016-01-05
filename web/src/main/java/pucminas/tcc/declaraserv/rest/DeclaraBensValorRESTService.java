/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pucminas.tcc.declaraserv.rest;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pucminas.asd.tcc.canonicoserv.Declaracao;
import pucminas.asd.tcc.canonicoserv.messages.MessageRequest;
import pucminas.asd.tcc.canonicoserv.messages.MessageResponse;
import pucminas.asd.tcc.canonicoserv.messages.ResponseSaveOrder;
import pucminas.tcc.declaraserv.service.DeclaraBensValorEJBService;

@Path("/declara")
@RequestScoped
public class DeclaraBensValorRESTService implements Serializable{

	private static final long serialVersionUID = -7988595249874838903L;
	
	@Inject
	private DeclaraBensValorEJBService declaraBensValorEJBService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String teste() {
		return "TESTE DECLARA BENS SERV";
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public MessageResponse processarDeclaracao(MessageRequest messageRequest) {
		System.out.println("Declaracao do " + messageRequest.getDeclaracao().getAgente().getCpf());
		Declaracao declaracao = messageRequest.getDeclaracao();
		// TODO: tentar clonar a declaracao antes de processar
		declaraBensValorEJBService.processarDeclaracao(declaracao.getBemDeclarado());
		ResponseSaveOrder responseSaveOrder = new ResponseSaveOrder(new Random().nextInt(), new Date());
		return new MessageResponse(responseSaveOrder);
	}

}

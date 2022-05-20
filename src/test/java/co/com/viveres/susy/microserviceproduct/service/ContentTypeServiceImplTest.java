package co.com.viveres.susy.microserviceproduct.service;

import static co.com.viveres.susy.microserviceproduct.DummyMock.contentEntityList;
import static co.com.viveres.susy.microserviceproduct.DummyMock.contentOutputDtoList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.viveres.susy.microservicecommons.dto.ContentDto;
import co.com.viveres.susy.microserviceproduct.repository.IContentRepository;
import co.com.viveres.susy.microserviceproduct.service.impl.ContentTypeServiceImpl;

@ExtendWith(MockitoExtension.class)
class ContentTypeServiceImplTest {
	
	@Mock
	private IContentRepository contentRepository;
	
	@InjectMocks
	private ContentTypeServiceImpl service;
	
	@Test
	void findAllContentTest() {
		when(this.contentRepository.findAll()).thenReturn(contentEntityList());
		
		List<ContentDto> contentOutputDtoListExpected = contentOutputDtoList();
		List<ContentDto> contentOutputDtoListActual = this.service.findAllContent();
		
		verify(this.contentRepository).findAll();
		
		assertFalse(contentOutputDtoListActual.isEmpty());
		assertTrue(contentOutputDtoListActual.size() > 0);
		//assertEquals(contentOutputDtoListExpected, contentOutputDtoListActual);
		
	}

}

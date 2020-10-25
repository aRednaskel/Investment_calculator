package pl.fintech.challenge1.backend.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.runner.RunWith
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import pl.fintech.challenge1.backend.controller.dto.InvestitionParams
import pl.fintech.challenge1.backend.domain.investment.DepositFrequency
import pl.fintech.challenge1.backend.domain.investment.GraphData
import pl.fintech.challenge1.backend.domain.investment.Investment
import pl.fintech.challenge1.backend.domain.investment.InvestmentService
import pl.fintech.challenge1.backend.domain.investment.Type
import org.springframework.http.MediaType
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import static org.hamcrest.Matchers.hasSize
import static org.mockito.Mockito.when
import static org.hamcrest.Matchers.is
import static org.mockito.BDDMockito.given
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@ActiveProfiles("test")
@WebMvcTest(controllers = [InvestmentController.class])
@AutoConfigureJsonTesters
class InvestmentControllerTest extends Specification {

    @Autowired
    MockMvc mvc

    @MockBean
    InvestmentService investmentService
    def objectMapper = new ObjectMapper()

    def "CreateInvestment"() {
        given:
        def investment = createInvestment()
        when(investmentService.save(investment)).thenReturn(investment)

        expect:
        mvc.perform(post(new URI("/api/investments/create"))
                    .content(objectMapper.writeValueAsString(investment))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("companyName", is("Company")))
            .andExpect(jsonPath("logoUrl", is("Logo")))
            .andExpect(jsonPath("type", is("BONDS")))
            .andExpect(jsonPath("initialCapital", is(1000)))
            .andExpect(jsonPath("duration", is(3)))
            .andExpect(jsonPath("additionalContribution", is(50)))
            .andExpect(jsonPath("depositFrequency", is("QUARTER")))
            .andExpect(jsonPath("returnRate", is(1)))

    }

    def "GetInvestition"() {
        given:
        def investments = createListOfInvestments(10)
        def investitionParams = getInvestitionParams()
        when(investmentService.getInvestments(investitionParams)).thenReturn(investments)
        expect:
        def result = mvc.perform(get(new URI("/api/investments"))
                .param("initialCapital", "9000")
                .param("duration", "13")
                .param("additionalContribution", "50")
                .param("depositFrequency", "QUARTER")
                .param("returnRate", "2"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn().getResponse().getContentAsString()

        def outputData = objectMapper.readValue(result, Investment[].class)
        assert outputData.size() == 9
        outputData.each {
            assert it.getInitialCapital() <= getInvestitionParams().getInitialCapital()
            assert it.getDuration() <= getInvestitionParams().getDuration()
            assert it.getAdditionalContribution() <= getInvestitionParams().getAdditionalContribution()
            assert it.getDepositFrequency() == getInvestitionParams().getDepositFrequency()
            assert it.getReturnRate() <= getInvestitionParams().getReturnRate()
        }
    }

    def "SummaryOfAllInvestments"() {
        given:
        def investments = createListOfInvestments(10)
        when(investmentService.getSummary(investments)).thenReturn(getGraphData())

        expect:
        def result = mvc.perform(post(new URI("/api/investments/return"))
                    .content(objectMapper.writeValueAsString(investments))
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()

        def outputData = objectMapper.readValue(result, GraphData.class)
        assert outputData.getProfits().size() == 12
    }

    Investment createInvestment() {
        return Investment.builder()
                .companyName("Company")
                .logoUrl("Logo")
                .type(Type.BONDS)
                .initialCapital(BigDecimal.valueOf(1000l))
                .duration(3)
                .additionalContribution(BigDecimal.valueOf(50l))
                .depositFrequency(DepositFrequency.QUARTER)
                .returnRate(BigDecimal.valueOf(1)).build()
    }

    List<Investment> createListOfInvestments(int number) {
        List<Investment> investments = new ArrayList<>();
        for (i in 1..<number) {
            investments.add(Investment.builder()
                    .companyName("Company")
                    .logoUrl("Logo")
                    .type(Type.BONDS)
                    .initialCapital(BigDecimal.valueOf(1000l * i))
                    .duration(3 + i)
                    .additionalContribution(BigDecimal.valueOf(50 * (i % 2)))
                    .depositFrequency(DepositFrequency.QUARTER)
                    .returnRate(BigDecimal.valueOf(0.2 * i)).build())
        }
        return investments;
    }

    InvestitionParams getInvestitionParams() {
        return new InvestitionParams(BigDecimal.valueOf(9000), 13,
                BigDecimal.valueOf(50), DepositFrequency.QUARTER, BigDecimal.valueOf(2l))
    }

    GraphData getGraphData() {
        return new GraphData([47, 95, 142, 190, 238, 285, 331, 374, 414, 447, 471, 485] as List<BigDecimal>)
    }

    @TestConfiguration
    static class StubConfig {
        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        InvestmentService investmentService() {
            return detachedMockFactory.Stub(InvestmentService)
        }
    }
}

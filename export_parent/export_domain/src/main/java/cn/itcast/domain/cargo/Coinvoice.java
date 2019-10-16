package cn.itcast.domain.cargo;

import java.io.Serializable;
import java.util.Date;

public class Coinvoice implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column co_invoice.INVOICE_ID
     *
     * @mbg.generated
     */
    private String invoiceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column co_invoice.SC_NO
     *
     * @mbg.generated
     */
    private String scNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column co_invoice.BL_NO
     *
     * @mbg.generated
     */
    private String blNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column co_invoice.TRADE_TERMS
     *
     * @mbg.generated
     */
    private String tradeTerms;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column co_invoice.STATE
     *
     * @mbg.generated
     */
    private Integer state;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column co_invoice.CREATE_BY
     *
     * @mbg.generated
     */
    private String createBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column co_invoice.CREATE_DEPT
     *
     * @mbg.generated
     */
    private String createDept;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column co_invoice.CREATE_TIME
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_invoice.INVOICE_ID
     *
     * @return the value of co_invoice.INVOICE_ID
     *
     * @mbg.generated
     */
    public String getInvoiceId() {
        return invoiceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_invoice.INVOICE_ID
     *
     * @param invoiceId the value for co_invoice.INVOICE_ID
     *
     * @mbg.generated
     */
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId == null ? null : invoiceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_invoice.SC_NO
     *
     * @return the value of co_invoice.SC_NO
     *
     * @mbg.generated
     */
    public String getScNo() {
        return scNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_invoice.SC_NO
     *
     * @param scNo the value for co_invoice.SC_NO
     *
     * @mbg.generated
     */
    public void setScNo(String scNo) {
        this.scNo = scNo == null ? null : scNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_invoice.BL_NO
     *
     * @return the value of co_invoice.BL_NO
     *
     * @mbg.generated
     */
    public String getBlNo() {
        return blNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_invoice.BL_NO
     *
     * @param blNo the value for co_invoice.BL_NO
     *
     * @mbg.generated
     */
    public void setBlNo(String blNo) {
        this.blNo = blNo == null ? null : blNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_invoice.TRADE_TERMS
     *
     * @return the value of co_invoice.TRADE_TERMS
     *
     * @mbg.generated
     */
    public String getTradeTerms() {
        return tradeTerms;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_invoice.TRADE_TERMS
     *
     * @param tradeTerms the value for co_invoice.TRADE_TERMS
     *
     * @mbg.generated
     */
    public void setTradeTerms(String tradeTerms) {
        this.tradeTerms = tradeTerms == null ? null : tradeTerms.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_invoice.STATE
     *
     * @return the value of co_invoice.STATE
     *
     * @mbg.generated
     */
    public Integer getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_invoice.STATE
     *
     * @param state the value for co_invoice.STATE
     *
     * @mbg.generated
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_invoice.CREATE_BY
     *
     * @return the value of co_invoice.CREATE_BY
     *
     * @mbg.generated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_invoice.CREATE_BY
     *
     * @param createBy the value for co_invoice.CREATE_BY
     *
     * @mbg.generated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_invoice.CREATE_DEPT
     *
     * @return the value of co_invoice.CREATE_DEPT
     *
     * @mbg.generated
     */
    public String getCreateDept() {
        return createDept;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_invoice.CREATE_DEPT
     *
     * @param createDept the value for co_invoice.CREATE_DEPT
     *
     * @mbg.generated
     */
    public void setCreateDept(String createDept) {
        this.createDept = createDept == null ? null : createDept.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column co_invoice.CREATE_TIME
     *
     * @return the value of co_invoice.CREATE_TIME
     *
     * @mbg.generated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column co_invoice.CREATE_TIME
     *
     * @param createTime the value for co_invoice.CREATE_TIME
     *
     * @mbg.generated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
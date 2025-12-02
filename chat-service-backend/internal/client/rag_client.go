package client

import (
	"bytes"
	"context"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"time"

	"github.com/gogf/gf/v2/frame/g"
)

// RagClient Python RAG 服务客户端
type RagClient struct {
	baseURL string
	client  *http.Client
}

// RetrieveRequest 召回请求
type RetrieveRequest struct {
	Question string  `json:"question"`
	KbIds    []int64 `json:"kb_ids"`
	TopK     int     `json:"top_k"`
}

// RagChunk 召回的文本块
type RagChunk struct {
	FileId   int64   `json:"file_id"`
	ChunkIdx int     `json:"chunk_idx"`
	Text     string  `json:"text"`
	Score    float64 `json:"score"`
}

// RetrieveResponse 召回响应
type RetrieveResponse struct {
	Success      bool       `json:"success"`
	Results      []RagChunk `json:"results"`
	TotalResults int        `json:"total_results"`
	Message      string     `json:"message"`
}

// NewRagClient 创建 RAG 客户端
func NewRagClient(ctx context.Context) *RagClient {
	// 从配置中读取 RAG 服务地址和超时时间
	serviceUrl := g.Cfg().MustGet(ctx, "rag.serviceUrl", "http://localhost:8001").String()
	timeout := g.Cfg().MustGet(ctx, "rag.timeout", 10000).Int()

	// 创建 HTTP Transport,禁用代理以访问本地服务
	transport := &http.Transport{
		Proxy: nil, // 禁用代理,直接连接
	}

	return &RagClient{
		baseURL: serviceUrl,
		client: &http.Client{
			Timeout:   time.Duration(timeout) * time.Millisecond,
			Transport: transport,
		},
	}
}

// Retrieve 调用文档召回服务
func (c *RagClient) Retrieve(ctx context.Context, question string, kbIds []int64, topK int) (*RetrieveResponse, error) {
	req := RetrieveRequest{
		Question: question,
		KbIds:    kbIds,
		TopK:     topK,
	}

	reqBody, err := json.Marshal(req)
	if err != nil {
		return nil, fmt.Errorf("marshal request failed: %w", err)
	}

	httpReq, err := http.NewRequestWithContext(ctx, "POST", c.baseURL+"/api/retrieve", bytes.NewReader(reqBody))
	if err != nil {
		return nil, fmt.Errorf("create request failed: %w", err)
	}
	httpReq.Header.Set("Content-Type", "application/json")

	resp, err := c.client.Do(httpReq)
	if err != nil {
		return nil, fmt.Errorf("http request failed: %w", err)
	}
	defer resp.Body.Close()

	if resp.StatusCode != http.StatusOK {
		body, _ := io.ReadAll(resp.Body)
		return nil, fmt.Errorf("http error %d: %s", resp.StatusCode, string(body))
	}

	var result RetrieveResponse
	if err := json.NewDecoder(resp.Body).Decode(&result); err != nil {
		return nil, fmt.Errorf("decode response failed: %w", err)
	}

	return &result, nil
}
